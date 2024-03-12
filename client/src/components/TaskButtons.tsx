import React from 'react';

const TaskButtons = ({ isEditing, onUpdate, onEdit, onDelete }: { isEditing: boolean, onUpdate: () => void, onEdit: () => void, onDelete: () => void }) => {
  return (
    <div className="task-manager-task-button-container">
      {isEditing ? (
        <button onClick={onUpdate} className="task-manager-task-button update">Update</button>
      ) : (
        <button onClick={onEdit} className="task-manager-task-button edit">Edit</button>
      )}
      <button onClick={onDelete} className="task-manager-task-button delete">Delete</button>
    </div>
  );
}

export default TaskButtons;
