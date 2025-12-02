import express from "express";
import cors from "cors";
import dotenv from "dotenv";
import { MongoClient } from "mongodb";
import { exec } from "child_process";

dotenv.config();

const uri = process.env.MONGO_URI;
const client = new MongoClient(uri);

const app = express();
app.use(cors());
app.use(express.json());


exec(`docker start ${process.env.MONGO_DATABASE_NAME} || docker compose up`, (error, stdout, stderr) => {
    if (error) {
        console.log("Error: No se a podido iniciar el contendor de mongo");
        return;
    }

    if (stderr) console.error(stderr);
    console.log("La base de datos se ha levantado correctamente", stdout);
});


app.get(`/putuaciones`, async (req, res) => {
    try {
        const puntuaciones = await client.db(process.env.MONGO_DATABASE_NAME).collection("puntuaciones").find({}).sort({ tiempo: 1 }).toArray();
        res.status(200).json(puntuaciones);
    } catch (error) {
        console.log(error);
        res.status(500).json({ error: "Error al obtener las puntuaciones" });
    }
});
app.get(`/putuaciones/:dificultad`, async (req, res) => {
    try {
        const puntuaciones = await client.db(process.env.MONGO_DATABASE_NAME).collection("puntuaciones").find({ dificultad: req.params.dificultad }).sort({ tiempo: 1 }).toArray();
        res.status(200).json(puntuaciones);
    } catch (error) {
        console.log(error);
        res.status(500).json({ error: "Error al obtener las puntuaciones" });
    }
});

app.post(`/putuaciones/insert`, async (req, res) => {
    try {
        const puntuacion = await client.db(process.env.MONGO_DATABASE_NAME).collection("puntuaciones").insertOne(req.body);
        res.status(200).json(puntuacion);
    } catch (error) {
        console.log(error);
        res.status(500).json({ error: "Error al insertar la puntuacion" });
    }
});


const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
    console.log(` Servidor corriendo en http://localhost:${PORT}`);
});



