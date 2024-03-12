interface Task {
  id?: number;
  name: string;
  description: string;
  status: string;
}

interface TaskProps {
  task: Task;
  handleUpdate: (task: Task) => void;
  handleDelete: (id: number) => void;
}
