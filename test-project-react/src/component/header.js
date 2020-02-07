import React, { Component } from "react";
import { NavLink } from "react-router-dom";
import "./header.css";

class Header extends Component {
  logoutUser() {
    sessionStorage.removeItem("authenticatedUser");
    window.location.href = "/login";
  }
  render() {
    return (
      <header>
        <NavLink to={"/converter"} activeClassName="active-link">
          Конвертер
        </NavLink>
        <NavLink to={"/history"} activeClassName="active-link">
          История
        </NavLink>
        <button type="button" onClick={() => this.logoutUser()}>
          Выход
        </button>
      </header>
    );
  }
}
export default Header;
