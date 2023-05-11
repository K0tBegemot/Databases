import React from "react";
import PropTypes from "prop-types"
import TypeField from "./TypeField";
import SendButton from "./SendButton";
import AddButton from "./AddButton";
import DataFieldRange from "./DataFieldRange";
import ValidTypeField from "./ValidTypeField";
import RequestType from "./RequestType";
import DynamicTable from "./DynamicTable";

class Form19 extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            headerNames : ["Статус запроса", "Сообщение"],
            rowArray : [{operationStatus : "", message : ""}],

            requestType : {value : "insert"},

            testLabName : {isValid : false, value : ""},
            testerId : {isValid : false, value : ""}
        }
    }

    handleRequestTypeChange = (event, requestType) => {
        this.setState({[event.target.name] : {isValid : this.state[event.target.name]["isValid"], value : requestType}});
    }

    handleFormChange = (event) => {
        this.setState({[event.target.name] : {isValid : this.state[event.target.name]["isValid"], value : event.target.value}});
    }

    handleValidity = (event) => {
        this.setState({[event.target.name] : {isValid : !this.state[event.target.name]["isValid"], value : this.state[event.target.name]["value"]}});
    }

    sendForm = (event) => {
        event.preventDefault();
        const formType = this.props.formType;
        fetch(`http://localhost:8080/form/18`,
            {
                method : "POST",
                mode : "cors",
                cache : "default",
                headers : {
                    "Content-Type" : "application/json",
                },
                body : JSON.stringify(this.state)
            }
        ).then((response) => {return response.json()}).then((response) => {
            console.log(response);
            this.setState({rowArray : [response]});
        });
    }

    render() {
        const formNumber = this.props.formNumber;
        return (
            <>
                <form className={"row justify-content-center"}>
                    <div className={"row col-6 shadow-lg rounded-2"}>
                        <label htmlFor={`TypeCompanyField-${formNumber}`} className={"form-label"}>
                            <h4>Работник тестовой лаборатории</h4>
                        </label>
                        <div id={`TypeCompanyField-${formNumber}`}>
                            <div>
                                <RequestType formNumber={formNumber} fieldNumber={0} changeForm={this.handleRequestTypeChange} nameField={"requestType"} currentValue={this.state.requestType.value}></RequestType>
                                <ValidTypeField formNumber={formNumber} fieldNumber={0} changeForm={this.handleFormChange} nameField={"testLabName"} label={"Имя тестовой лаборатории:"} placeholder={"Введите уникальное имя тестовой лаборатории для редактирования, иначе выполнится вставка"} changeValidity={this.handleValidity} valid={this.state.testLabName.isValid}></ValidTypeField>
                                <ValidTypeField formNumber={formNumber} fieldNumber={0} changeForm={this.handleFormChange} nameField={"testerId"} label={"Идентификатор тестировщика тестовой лаборатории:"} placeholder={"Введите уникальный идентификатор тестировщика для редактирования, иначе выполнится вставка"} changeValidity={this.handleValidity} valid={this.state.testerId.isValid}></ValidTypeField>
                                <div className={"row"}>
                                    <div className={"col"}>
                                        <SendButton sendForm={this.sendForm}></SendButton>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <DynamicTable headerNamesList={this.state.headerNames} BodyValues={this.state.rowArray}></DynamicTable>
                    </div>
                </form>
            </>
        );
    }
}

Form19.propTypes = {
    formNumber : PropTypes.number.isRequired,
    formType : PropTypes.number.isRequired,
};

export default Form19;