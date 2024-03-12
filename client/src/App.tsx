import { useEffect, useState } from 'react';
import './App.css';
import taskManagerAPI from './services/api';
import TaskComponent from './components/TaskComponent';

function App() {
  const [tasks, setTasks] = useState<Task[]>([]);
  const [newTaskName, setNewTaskName] = useState<string>('');
  const [newTaskDescription, setNewTaskDescription] = useState<string>('');
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);

  const fetchTasks = () => {
    setIsLoading(true);
    taskManagerAPI.getTasks()
      .then(response => setTasks(response.data))
      .catch(error => setError(error.message))
      .finally(() => setIsLoading(false));
  };

  useEffect(fetchTasks, []);

  const handleUpdate = (task: Task) => {
    taskManagerAPI.updateTask(task)
      .then(fetchTasks)
      .catch(error => setError(error.message));
  };

  const handleDelete = (id: number) => {
    taskManagerAPI.deleteTask(id)
      .then(fetchTasks)
      .catch(error => setError(error.message));
  };

  const handleButtonClick = () => {
    const newTask = {
      name: newTaskName,
      description: newTaskDescription,
      status: 'TODO'
    };
    taskManagerAPI.createTask(newTask)
      .then(() => {
        setNewTaskName('');
        setNewTaskDescription('');
        fetchTasks();
      })
      .catch(error => setError(error.message));
  };

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setNewTaskName(event.target.value);
  };

  const handleDescriptionChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setNewTaskDescription(event.target.value);
  };

  if (isLoading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error}</div>;
  }

  return (
    <div className="App task-manager-app">
      <input type="text" value={newTaskName} onChange={handleInputChange} placeholder="Task Name" className="task-manager-input" />
      <input type="text" value={newTaskDescription} onChange={handleDescriptionChange} placeholder="Task Description" className="task-manager-input" />
      <button onClick={handleButtonClick} className="task-manager-button">Create Task</button>
      <ul className="task-manager-list">
        {tasks.map(task => (
          <li key={task.id} className="task-manager-item">
            <TaskComponent task={task} handleUpdate={handleUpdate} handleDelete={handleDelete} />
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
