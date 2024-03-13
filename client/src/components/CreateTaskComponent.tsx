import React, { useState } from 'react';

interface CreateTaskProps {
  onTaskCreated: (name: string, description: string, status: string) => void;
}

const CreateTaskComponent: React.FC<CreateTaskProps> = ({ onTaskCreated }) => {
  const [newTaskName, setNewTaskName] = useState<string>('');
  const [newTaskDescription, setNewTaskDescription] = useState<string>('');

  const handleButtonClick = () => {
    onTaskCreated(newTaskName, newTaskDescription, "TODO");
    setNewTaskName('');
    setNewTaskDescription('');
  };

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setNewTaskName(event.target.value);
  };

  const handleDescriptionChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setNewTaskDescription(event.target.value);
  };

  return (
    <div className="task-manager-create-wrapper">
      <input type="text" value={newTaskName} onChange={handleInputChange} placeholder="Task Name" className="task-manager-create-input" />
      <input type="text" value={newTaskDescription} onChange={handleDescriptionChange} placeholder="Task Description" className="task-manager-create-input" />
      <button onClick={handleButtonClick} className="task-manager-create-button">Create Task</button>
    </div>
  );
}

export default CreateTaskComponent;
