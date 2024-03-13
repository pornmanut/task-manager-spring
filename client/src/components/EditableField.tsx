import React from 'react';

interface EditableFieldProps {
  isEditing: boolean;
  text: string;
  className: string;
  onChange: (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => void;
}

const EditableField = ({ isEditing, text, className, onChange }: EditableFieldProps) => {
  return isEditing ? (
    <input className={`text-editable ${className}`} type="text" value={text} onChange={onChange} />
  ) : (
    <p className={`text-editable ${className}`}>{text}</p>
  );
}

export default EditableField;
