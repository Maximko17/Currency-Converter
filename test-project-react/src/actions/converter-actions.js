import axios from "axios";

export function getCurrency() {
  return axios.get(`/converter/currency`);
}

export function convert(converter) {
  return axios.post(`/converter/convert`, converter);
}
