import React, { useState, useCallback } from 'react';
import EditableField from './EditableField';
import StatusSelect from './StatusSelect';
import TaskButtons from './TaskButtons';

const TaskComponent = ({ task, handleUpdate, handleDelete }: TaskProps) => {
  const [selectedStatus, setSelectedStatus] = useState(task.status);
  const [isEditing, setIsEditing] = useState(false);
  const [editedName, setEditedName] = useState(task.name);
  const [editedDescription, setEditedDescription] = useState(task.description);

  const handleChange = useCallback((event: React.ChangeEvent<HTMLSelectElement>) => {
    setSelectedStatus(event.target.value);
  }, []);

  const handleNameChange = useCallback((event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    setEditedName(event.target.value);
  }, []);

  const handleDescriptionChange = useCallback((event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    setEditedDescription(event.target.value);
  }, []);

  const handleClick = useCallback(() => {
    handleUpdate({ ...task, status: selectedStatus, name: editedName, description: editedDescription });
    setIsEditing(false);
  }, [handleUpdate, task, selectedStatus, editedName, editedDescription]);

  const handleEditClick = useCallback(() => {
    setIsEditing(true);
  }, []);

  const handleDeleteClick = useCallback(() => {
    handleDelete(task.id!);
  }, [handleDelete, task.id]);

  return (
    <div className='taskComponent task-manager-task'>
      <EditableField isEditing={isEditing} text={editedName} onChange={handleNameChange} />
      <EditableField isEditing={isEditing} text={editedDescription} onChange={handleDescriptionChange} />
      <StatusSelect isEditing={isEditing} status={selectedStatus} onChange={handleChange} />
      <TaskButtons isEditing={isEditing} onUpdate={handleClick} onEdit={handleEditClick} onDelete={handleDeleteClick} />
    </div>
  );
}

export default TaskComponent;
