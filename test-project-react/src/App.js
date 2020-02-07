import React, { Component } from "react";
import "./App.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import LoginPage from "./component/login-page";
import Converter from "./component/converter";
import History from "./component/history";
import AuthenticatedRoute from "./utils/authenticated-route";
import setToken from "./utils/authHeader";

const authToken = sessionStorage.getItem("authenticatedUser");
if (authToken) {
  setToken(authToken);
}
class App extends Component {
  render() {
    return (
      <Router>
        <Route exact path="/" component={LoginPage} />
        <Route exact path="/login" component={LoginPage} />
        <AuthenticatedRoute exact path="/converter" component={Converter} />
        <AuthenticatedRoute exact path="/history" component={History} />
      </Router>
    );
  }
}

export default App;
