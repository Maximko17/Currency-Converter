import React, { Component } from "react";
import "./converter.css";
import Header from "./header";
import { getCurrency, convert } from "../actions/converter-actions";
import classNames from "classnames";

class Converter extends Component {
  state = {
    allCurrency: [],
    converter: {
      startCurrencyId: "",
      endCurrencyId: "",
      startAmount: "",
      endAmount: 0
    },
    error: false
  };

  componentDidMount() {
    getCurrency().then(res => {
      this.setState({
        allCurrency: res.data
      });
    });
  }

  onChange = e => {
    this.setState({
      converter: {
        ...this.state.converter,
        [e.target.name]: e.target.value
      }
    });
  };

  onClick = () => {
    const { converter } = this.state;
    const str = converter.startAmount.replace(",", ".");
    converter.startAmount = str;
    convert(converter)
      .then(res => {
        this.setState({
          endAmount: res.data,
          error: false
        });
      })
      .catch(() => {
        this.setState({
          error: true
        });
      });
  };

  render() {
    const {
      startCurrency,
      endCurrency,
      startAmount,
      endAmount,
      allCurrency,
      error
    } = this.state;
    return (
      <div>
        <Header />
        <div className="wrapper-converter">
          <div className="converter">
            <h1>Конвертер валют</h1>
            <div
              className={classNames("error-message", {
                show: error
              })}
            >
              <p>Проверьте правильность введеных данных</p>
            </div>
            <div className="currency">
              <label>
                <span>Исходная валюта</span>
                <select
                  name="startCurrencyId"
                  onChange={this.onChange}
                  value={startCurrency}
                >
                  <option>Выбрать...</option>
                  {allCurrency.map(({ id, name, charCode }) => {
                    return (
                      <option
                        value={id}
                        key={id}
                      >{`${charCode} (${name})`}</option>
                    );
                  })}
                </select>
              </label>
              <label>
                <span>Целевая валюта</span>
                <select
                  name="endCurrencyId"
                  onChange={this.onChange}
                  value={endCurrency}
                >
                  <option>Выбрать...</option>
                  {allCurrency.map(({ id, name, charCode }) => {
                    return (
                      <option
                        value={id}
                        key={id}
                      >{`${charCode} (${name})`}</option>
                    );
                  })}
                </select>
              </label>
            </div>
            <div className="amount">
              <label>
                <span>Исходная сумма</span>
                <input
                  type="text"
                  placeholder="Сумма"
                  name="startAmount"
                  onChange={this.onChange}
                  value={startAmount}
                />
              </label>
              <label>
                <span>Итог перевода</span>
                <span id="endAmount">{endAmount !== 0 ? endAmount : "-"}</span>
              </label>
            </div>

            <div className="convert-button">
              <button onClick={this.onClick}>Конвертировать</button>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
export default Converter;
