import React from 'react';

const StatusSelect = ({ isEditing, status, onChange }: { isEditing: boolean, status: string, onChange: (event: React.ChangeEvent<HTMLSelectElement>) => void }) => {
  return isEditing ? (
    <div>
      <select value={status} onChange={onChange}>
        <option value="TODO">TODO</option>
        <option value="DOING">DOING</option>
        <option value="DONE">DONE</option>
      </select>
    </div>
  ) : (
    <p>Status: {status}</p>
  );
}

export default StatusSelect;
