import axios, { AxiosInstance, AxiosResponse } from "axios";

class TaskManagerAPI {
  private api: AxiosInstance;
  constructor(url: string) {
    this.api = axios.create({
      baseURL: url,
    });
  }

  public async getTasks(): Promise<AxiosResponse<Task[]>> {
    return this.api.get("/tasks");
  }

  public async getTask(id: number): Promise<AxiosResponse<Task>> {
    return this.api.get(`/tasks/${id}`);
  }

  public async createTask(task: Task): Promise<AxiosResponse<Task>> {
    return this.api.post("/tasks", task);
  }

  public async updateTask(task: Task): Promise<AxiosResponse<Task>> {
    return this.api.put(`/tasks/${task.id}`, task);
  }

  public async deleteTask(id: number): Promise<AxiosResponse<void>> {
    return this.api.delete(`/tasks/${id}`);
  }
}

const taskManagerAPI = new TaskManagerAPI(
  process.env.REACT_APP_TASK_MANAGER_API ?? "http://localhost:3001"
);

export default taskManagerAPI;
