import {addUser} from "./main.js";

const userTable = document.querySelector('#userTable');

fetch(`http://localhost:8080/api/v1/users/${userId}`)
    .then(res => res.json())
    .then(user => addUser(userTable,user));