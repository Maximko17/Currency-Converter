import React, { Component } from "react";
import "./history.css";
import Header from "./header";
import { getHistory } from "../actions/history-actions";
import { getCurrency } from "../actions/converter-actions";

class History extends Component {
  state = {
    allCurrency: [],
    historyRows: [],
    date: "",
    startCurrency: "",
    endCurrency: ""
  };

  componentDidMount() {
    const { startCurrency, endCurrency, date } = this.state;
    getHistory(date, startCurrency, endCurrency).then(res => {
      this.setState({
        historyRows: res.data
      });
    });
    getCurrency().then(res => {
      this.setState({
        allCurrency: res.data
      });
    });
  }
  onChange = e => {
    this.setState({
      [e.target.name]: e.target.value
    });
  };
  onClick = () => {
    const { startCurrency, endCurrency, date } = this.state;
    getHistory(date, startCurrency, endCurrency).then(res => {
      this.setState({
        historyRows: res.data
      });
    });
  };
  render() {
    const {
      date,
      startCurrency,
      endCurrency,
      historyRows,
      allCurrency
    } = this.state;
    return (
      <div>
        <Header />
        <div className="wrapper-history">
          <div className="history">
            <h1>История</h1>
            <div className="filters">
              <input
                type="date"
                name="date"
                onChange={this.onChange}
                value={date}
              />
              <label>
                <span>Из</span>
                <select
                  name="startCurrency"
                  onChange={this.onChange}
                  value={startCurrency}
                >
                  <option value="">Выбрать...</option>
                  {allCurrency.map(({ id, name, charCode }) => {
                    return (
                      <option
                        value={charCode}
                        key={id}
                      >{`${charCode} (${name})`}</option>
                    );
                  })}
                </select>
              </label>
              <label>
                <span>В</span>
                <select
                  name="endCurrency"
                  onChange={this.onChange}
                  value={endCurrency}
                >
                  <option value="">Выбрать...</option>
                  {allCurrency.map(({ id, name, charCode }) => {
                    return (
                      <option
                        value={charCode}
                        key={id}
                      >{`${charCode} (${name})`}</option>
                    );
                  })}
                </select>
              </label>
              <button onClick={this.onClick}>Поиск</button>
            </div>
            <div>
              <table>
                <thead>
                  <tr>
                    <th>Исходная валюта</th>
                    <th>Цеоевая валюта</th>
                    <th>Исходная сумма</th>
                    <th>Получаемая сумма</th>
                    <th>Дата</th>
                  </tr>
                </thead>
                <tbody>
                  {historyRows.map(
                    (
                      {
                        startCurrency,
                        endCurrency,
                        startAmount,
                        endAmount,
                        date
                      },
                      index
                    ) => {
                      return (
                        <tr key={index}>
                          <td>{startCurrency}</td>
                          <td>{endCurrency}</td>
                          <td>{startAmount}</td>
                          <td>{endAmount}</td>
                          <td>{date}</td>
                        </tr>
                      );
                    }
                  )}
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
export default History;
