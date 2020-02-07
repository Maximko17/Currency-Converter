import React, { Component } from "react";
import "./login-page.css";
import { loginUser, registerUserAuthentication } from "../actions/user-actions";
import classNames from "classnames";

class LoginPage extends Component {
  state = {
    login: "",
    password: "",
    error: false
  };

  componentDidMount() {
    const authToken = sessionStorage.getItem("authenticatedUser");
    if (authToken) {
      this.props.history.push("/converter");
    }
  }

  onChange = e => {
    this.setState({
      [e.target.name]: e.target.value
    });
  };
  onSubmit = e => {
    e.preventDefault();
    const { login, password } = this.state;
    if(!login || !password ){
        this.setState({
          error:true
        })
    }else{
    loginUser(login, password)
      .then(() => {
        registerUserAuthentication(login, password);
        window.location.href = "/converter";
      })
      .catch(() => {
        this.setState({
          error: true
        });
      });
    }
  };

  render() {
    const { login, password, error } = this.state;
    return (
      <div className="wrapper-login">
        <form onSubmit={this.onSubmit} className="login-page">
          <h1>Конвертер валют</h1>
          <div
            className={classNames("error-message", {
              show: error
            })}
          >
            <p>Неправильный логин или пароль</p>
          </div>
          <input
            type="text"
            placeholder="Логин"
            name="login"
            onChange={this.onChange}
            value={login}
          />
          <input
            type="password"
            placeholder="Пароль"
            name="password"
            onChange={this.onChange}
            value={password}
          />
          <button type="submit">Войти</button>
        </form>
      </div>
    );
  }
}
export default LoginPage;
