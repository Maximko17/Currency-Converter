import axios from "axios";

export const SESSION_ATTRIBUTE_USER_LOGIN = "authenticatedUser";

export function loginUser(login, password) {
  const authToken = createBasicAuthToken(login, password);
  return axios.get(`/basicauth`, {
    headers: { authorization: authToken }
  });
}

export function registerUserAuthentication(login, password) {
  const authToken = createBasicAuthToken(login, password);

  sessionStorage.setItem(SESSION_ATTRIBUTE_USER_LOGIN, authToken);
  setAxiosInterceptors(authToken);
}

export function setAxiosInterceptors(authToken) {
  axios.interceptors.request.use(config => {
    if (isUserLoggedIn()) {
      config.headers.authorization = authToken;
    }
    return config;
  });
}

export function createBasicAuthToken(username, password) {
  return "Basic " + window.btoa(username + ":" + password);
}

export function isUserLoggedIn() {
  let user = sessionStorage.getItem(SESSION_ATTRIBUTE_USER_LOGIN);
  if (user === null) return false;
  return true;
}

export function logout() {
  sessionStorage.removeItem(SESSION_ATTRIBUTE_USER_LOGIN);
}
