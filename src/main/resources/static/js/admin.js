import {addUser,updateUser,deleteUser,fillForm,userJsonFromForm,addRole} from '/js/main.js';

const allUsersTable = document.querySelector('#allUsersTable');
const userTable = document.querySelector('#userTable');
const selects = document.querySelectorAll('form div select[name=roles]');
const newForm = document.querySelector('#newForm');
const modalEdit = document.querySelector('#editModal');
const modalDelete= document.querySelector('#deleteModal');
const editForm = modalEdit.querySelector('form');
const deleteForm = modalDelete.querySelector('form');

let currentUser;
let usersStorage = {};
let rolesStorage = {};

//Bootstrap elements for manage(hide, show)
let bootstrapModalEdit,bootstrapModalDelete,bootstrapTab
document.addEventListener('DOMContentLoaded',()=>{
    bootstrapModalEdit = new bootstrap.Modal(modalEdit);
    bootstrapModalDelete = new bootstrap.Modal(modalDelete);
    bootstrapTab = new bootstrap.Tab(document.querySelector('#nav-home-tab'));
})

//Show edit modal
modalEdit.addEventListener('show.bs.modal', (e) => {
    const userId = e.relatedTarget.closest('tr[userId]').getAttribute('userId');
    fillForm(editForm,usersStorage[userId]);
});

//Show delete modal
modalDelete.addEventListener('show.bs.modal', (e) => {
    const userId = e.relatedTarget.closest('tr[userId]').getAttribute('userId');
    fillForm(deleteForm,usersStorage[userId]);
});


//Fetch get all users
fetch('http://localhost:8080/api/v1/users')
    .then(res => {
        if(res.ok){
            res.json()
                .then(data => data.forEach(user =>{
                addUser(allUsersTable,user)
                if(user.id === Number(userId)){
                    currentUser = user;
                    addUser(userTable,user)
                }
                usersStorage[user.id] = user;
            }))
        }
    }).catch(error => {
    console.log(error);
});


//Fetch add user
newForm.addEventListener('submit', (e) => {
    e.preventDefault();
    const user = userJsonFromForm(newForm,rolesStorage);
    fetch('http://localhost:8080/api/v1/users', {
        headers: {
            'Content-type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify(user)
    }).then(res => {
        if (res.ok) {
            newForm.reset();
            user.id = Number(res.headers.get('UserId'));
            addUser(allUsersTable,user);
            usersStorage[user.id] = user;
            bootstrapTab.show();
        }
    }).catch(error => {
        console.log(error);
    });
});

//Fetch update user
editForm.addEventListener('submit', (e) => {
    e.preventDefault();
    const user = userJsonFromForm(editForm,rolesStorage);
    user.id = Number(editForm.id.value);
    fetch(`http://localhost:8080/api/v1/users/${user.id}`, {
        headers: {
            'Content-type': 'application/json'
        },
        method: 'PUT',
        body: JSON.stringify(user)
    }).then(res => {
        if (res.ok) {
            updateUser(allUsersTable,user);
            if(user.id === currentUser.id){
                updateUser(userTable,user)
            }
            usersStorage[user.id] = user;
            bootstrapModalEdit.hide();
        }
    }).catch(error => {
        console.log(error);
    });
});

//Fetch delete user
deleteForm.addEventListener('submit', (e) => {
    e.preventDefault();
    const user = userJsonFromForm(deleteForm,rolesStorage);
    user.id = Number(deleteForm.id.value);
    fetch(`http://localhost:8080/api/v1/users/${user.id}`, {
        headers: {
            'Content-type': 'application/json'
        },
        method: 'DELETE',
        body: JSON.stringify(user)
    }).then(res => {
        if (res.ok) {
            deleteUser(allUsersTable,user);
            if(user.id === currentUser.id){
                deleteUser(userTable,user)
            }
            delete usersStorage[user.id];
            bootstrapModalDelete.hide();
        }
    }).catch(error => {
        console.log(error);
    });
});


//Fetch get all roles
fetch('http://localhost:8080/api/v1/roles')
    .then(res => res.json())
    .then(data => data.forEach(role => {
        addRole(selects,role)
        rolesStorage[role.name] = role;
    }))








