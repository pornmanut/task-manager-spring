import React from 'react';

const EditableField = ({ isEditing, text, onChange }: { isEditing: boolean, text: string, onChange: (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => void }) => {
  return isEditing ? (
    <input type="text" value={text} onChange={onChange} />
  ) : (
    <h2>{text}</h2>
  );
}

export default EditableField;
