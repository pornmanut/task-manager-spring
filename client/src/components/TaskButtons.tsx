import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEdit, faTrashAlt, faCheckCircle } from '@fortawesome/free-solid-svg-icons';

const TaskButtons = ({ isEditing, onUpdate, onEdit, onDelete }: { isEditing: boolean, onUpdate: () => void, onEdit: () => void, onDelete: () => void }) => {
  return (
    <div className="task-button-container">
      {isEditing ? (
        <button onClick={onUpdate} className="task-button update"><FontAwesomeIcon icon={faCheckCircle} size="lg"/></button>
      ) : (
        <button onClick={onEdit} className="task-button edit"><FontAwesomeIcon icon={faEdit} size="lg"/></button>
      )}
      <button onClick={onDelete} className="task-button delete"><FontAwesomeIcon icon={faTrashAlt} size="lg"/></button>
    </div>
  );
}

export default TaskButtons;
