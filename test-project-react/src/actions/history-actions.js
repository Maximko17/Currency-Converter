import axios from "axios";

export function getHistory(date, startCurrency, endCurrency) {
  return axios.get(
    `/history?date=${date}&startCurrency=${startCurrency}&endCurrency=${endCurrency}`
  );
}
