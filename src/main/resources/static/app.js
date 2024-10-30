const baseURL = 'http://localhost:8080/api/todos';

// Fetch and display all tasks
async function fetchTasks() {
    try {
        const response = await fetch(baseURL);
        if (!response.ok) {
            throw new Error(`Failed to fetch tasks: ${response.statusText}`);
        }
        const tasks = await response.json();
        const taskList = document.getElementById('taskList');
        taskList.innerHTML = '';

        tasks.forEach(task => {
            const li = document.createElement('li');
            li.textContent = task.task;
            li.setAttribute('data-id', task.id);
            li.onclick = () => deleteTask(task.id);
            taskList.appendChild(li);
        });
    } catch (error) {
        console.error(error);
    }
}

// Add a new task
async function addTask() {
    const taskInput = document.getElementById('taskInput');
    if (taskInput.value.trim() === '') {
        alert('Please enter a task.');
        return;
    }
    const task = { task: taskInput.value.trim() };

    try {
        const response = await fetch(baseURL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(task)
        });

        if (!response.ok) {
            throw new Error(`Failed to add task: ${response.statusText}`);
        }

        taskInput.value = '';
        await fetchTasks(); // Refresh the list
    } catch (error) {
        console.error(error);
    }
}

// Delete a task by ID
async function deleteTask(id) {
    try {
        const response = await fetch(`${baseURL}/${id}`, {
            method: 'DELETE'
        });

        if (!response.ok) {
            throw new Error(`Failed to delete task: ${response.statusText}`);
        }

        await fetchTasks(); // Refresh the list
    } catch (error) {
        console.error(error);
    }
}

// Initialize the app
document.addEventListener('DOMContentLoaded', fetchTasks);
