import axios, { AxiosInstance } from "axios";

const instance: AxiosInstance = axios.create({
  baseURL: "https://lugtil68dh.execute-api.ap-southeast-1.amazonaws.com/Prod/",
});

export default instance;
