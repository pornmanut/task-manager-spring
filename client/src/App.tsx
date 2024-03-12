import { useEffect,useState } from 'react';
import './App.css';
import logo from './logo.svg';
import api from './services/api';

interface Task {
  id: number;
  name: string;
  description: string;
  status: string;
}

function App() {
  const [tasks, setTasks] = useState<Task[]>([]);

  useEffect(() => {
    api.get<Task[]>('/tasks')
      .then(response => setTasks(response.data))
      .catch(error => console.error(error));
  }, []);
  
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.tsx</code> and save to reload.
        </p>
        <ul>
          {tasks.map(task => (
            <li key={task.id}>
              {task.name}
              {task.description}
              {task.status}
            </li>
          ))}
        </ul>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
