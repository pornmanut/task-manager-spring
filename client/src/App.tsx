import { useEffect, useState } from 'react';
import './App.css';
import taskManagerAPI from './services/api';
import TaskComponent from './components/TaskComponent';
import LoadingPage from './components/LoadingPage';
import CreateTaskComponent from './components/CreateTaskComponent';

function App() {
  const [tasks, setTasks] = useState<Task[]>([]);
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

  const handleCreate = (name: string, description: string, status: string) => {
    const newTask = {
      name,
      description,
      status
    };
    taskManagerAPI.createTask(newTask)
    .then(fetchTasks)
    .catch(error => console.error(error.message));
  };

  if (isLoading) {
    return <LoadingPage />;
  }

  if (error) {
    return <div>Error: {error}</div>;
  }

  return (
    <div className="task-manager-app">
      <h1>Task Manager Sprint</h1>
      <CreateTaskComponent onTaskCreated={handleCreate} />
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
