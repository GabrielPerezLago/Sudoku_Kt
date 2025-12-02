db = db.getSiblingDB('sudoku');

db.createCollection('puntuaciones');
db.puntuaciones.insertOne({
    nombre: 'pablo',
    tiempo: "20:20",
    dificultad: "facil",

});
