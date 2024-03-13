import React from 'react';

interface StatusSelectProps {
  isEditing: boolean;
  status: string;
  onChange: (event: React.ChangeEvent<HTMLSelectElement>) => void;
}

const getStatusClass = (status: string) => {
   if (status === 'TODO') return 'status-todo';
   if (status === 'DOING') return 'status-doing';
   return 'status-done';
}

const StatusSelect = ({ isEditing, status, onChange }: StatusSelectProps) => {
  const statusClass = getStatusClass(status);

  return isEditing ? (
    <div className={`status-select ${statusClass}`}>
      <select value={status} onChange={onChange}>
        <option value="TODO">TODO</option>
        <option value="DOING">DOING</option>
        <option value="DONE">DONE</option>
      </select>
    </div>
  ) : (
    <p className={`status-text ${statusClass}`}>{status}</p>
  );
}

export default StatusSelect;
