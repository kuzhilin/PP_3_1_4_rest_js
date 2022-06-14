// HTML operations
//Add
export function addUser(table,user) {
    let body = `
                <th>${user.id}</th>
                <td name="name">${user.name}</td>
                <td name="lastName">${user.lastName}</td>
                <td name="age">${user.age}</td>
                <td name="email">${user.email}</td>
                <td name="roles">${user.roles.map(value => value.name).sort().toString()}</td>
            `
    let buttons = `
                <td><button type="button" class="btn btn-info edit-button" data-bs-toggle="modal" data-bs-target="#editModal">Edit</button></td>
                <td><button type="button" class="btn btn-danger edit-button" data-bs-toggle="modal" data-bs-target="#deleteModal">Delete </button></td> 
    `

    table.querySelector('tbody').insertAdjacentHTML('beforeend', `
                <tr userId=${user.id}>
                 ${body}
                 ${table.id === 'allUsersTable' ? buttons : ''}
                </tr>
            `);
};

//Update
export function updateUser(table,updatedUser) {
    const row = table.querySelector(`tbody tr[userId="${updatedUser.id}"]`);
    row.querySelector('td[name=name]').textContent = updatedUser.name;
    row.querySelector('td[name=lastName]').textContent = updatedUser.lastName;
    row.querySelector('td[name=age]').textContent = updatedUser.age;
    row.querySelector('td[name=email]').textContent = updatedUser.email;
    row.querySelector('td[name=roles]').textContent = updatedUser.roles.map(value => value.name).sort().toString();
};

//Delete
export function deleteUser(table,deletedUser) {
    table.querySelector(`tbody tr[userId="${deletedUser.id}"]`).remove();
};

//Roles to all selects
export function addRole(selects,role) {
    selects.forEach(select => {
        select.insertAdjacentHTML('beforeend', `<option>${role.name}</option>`);
    });
};

//Other operations
//Form values to JSON
export function userJsonFromForm(form,rolesStorage){
    return {
        name: form.querySelector('input[name=name]').value,
        lastName: form.querySelector('input[name=lastName]').value,
        age: form.querySelector('input[name=age]').value,
        email: form.querySelector('input[name=email]').value,
        password: form.querySelector('input[name=password]').value,
        roles: [...form.querySelector('select[name=roles]').options].filter(option => option.selected).map(option => rolesStorage[option.value])
    };
};

//Fill form
export function fillForm(form,user){
    form.id.value = user.id;
    form.name.value = user.name;
    form.lastName.value = user.lastName;
    form.age.value = user.age;
    form.email.value = user.email;
    form.password.value = user.password;
    [...form.querySelector('select[name=roles]').options].forEach(option => {
        option.selected = user.roles.some(el => el.name === option.value);
    });
};