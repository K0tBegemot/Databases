import React from "react";
import PropTypes from "prop-types"
import TypeField from "./TypeField";
import SendButton from "./SendButton";
import AddButton from "./AddButton";
import DataFieldRange from "./DataFieldRange";
import ValidTypeField from "./ValidTypeField";
import ValidDatePicker from "./ValidDatePicker";
import RequestType from "./RequestType";
import DynamicTable from "./DynamicTable";

class Form13 extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            headerNames : ["Статус запроса", "Сообщение"],
            rowArray : [{operationStatus : "", message : ""}],

            requestType : {value : "insert"},

            workerId : {valid : false, value : ""},
            firstName : {valid : false, value : ""},
            secondName : {valid : false, value : ""},
            brigadeId : {valid : false, value : ""},
            startDate : {valid : false, value : new Date().toISOString()},
            endDate : {valid : false, value : new Date().toISOString()},
            employeeTypeName : {valid : false, value : ""},
            employeeTypeSpecName : {valid : false, value : ""}
        }
    }

    handleRequestTypeChange = (event, requestType) => {
        this.setState({[event.target.name] : {valid : this.state[event.target.name]["valid"], value : requestType}});
    }

    handleFormChange = (event) => {
        this.setState({[event.target.name] : {valid : this.state[event.target.name]["valid"], value : event.target.value}});
    }

    handleDateChange = (date, name) =>{
        this.setState({[name] : {valid : this.state[name]["valid"], value : date.toISOString()}});
    }

    handleValidity = (event) => {
        this.setState({[event.target.name] : {valid : !this.state[event.target.name]["valid"], value : this.state[event.target.name]["value"]}});
    }

    sendForm = (event) => {
        event.preventDefault();
        const formType = this.props.formType;
        fetch(`http://localhost:8080/form/12`,
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
                            <h4>Уникальный идентификатор работника, имя, фамилия, ключ бригады, даты начала и конца работы, должность и специальность работника</h4>
                        </label>
                        <div id={`TypeCompanyField-${formNumber}`}>
                            <div>
                                <RequestType formNumber={formNumber} fieldNumber={0} changeForm={this.handleRequestTypeChange} nameField={"requestType"} currentValue={this.state.requestType.value}></RequestType>
                                <ValidTypeField formNumber={formNumber} fieldNumber={0} changeForm={this.handleFormChange} nameField={"workerId"} label={"Уникальный ключ работника:"} placeholder={"Введите уникальный ключ для редактирования, иначе выполнится вставка"} changeValidity={this.handleValidity} valid={this.state.workerId.valid}></ValidTypeField>
                                <ValidTypeField formNumber={formNumber} fieldNumber={0} changeForm={this.handleFormChange} nameField={"firstName"} label={"Имя работника:"} placeholder={"Введите имя работника"} changeValidity={this.handleValidity} valid={this.state.firstName.valid}></ValidTypeField>
                                <ValidTypeField formNumber={formNumber} fieldNumber={0} changeForm={this.handleFormChange} nameField={"secondName"} label={"Фамилия работника:"} placeholder={"Введите фамилию работника"} changeValidity={this.handleValidity} valid={this.state.secondName.valid}></ValidTypeField>
                                <ValidTypeField formNumber={formNumber} fieldNumber={0} changeForm={this.handleFormChange} nameField={"brigadeId"} label={"Уникальный ключ бригады:"} placeholder={"Введите уникальный ключ бригады"} changeValidity={this.handleValidity} valid={this.state.brigadeId.valid}></ValidTypeField>
                                <ValidDatePicker formNumber={formNumber} fieldNumber={0} changeForm={this.handleDateChange} changeValidity={this.handleValidity} valid={this.state.startDate.valid} nameField={"startDate"} label={"Дата начала работы:"}></ValidDatePicker>
                                <ValidDatePicker formNumber={formNumber} fieldNumber={0} changeForm={this.handleDateChange} changeValidity={this.handleValidity} valid={this.state.endDate.valid} nameField={"endDate"} label={"Дата конца работы:"}></ValidDatePicker>
                                <ValidTypeField formNumber={formNumber} fieldNumber={0} changeForm={this.handleFormChange} nameField={"employeeTypeName"} label={"Должность работника:"} placeholder={"Введите должность работника (Engineer или Worker)"} changeValidity={this.handleValidity} valid={this.state.employeeTypeName.valid}></ValidTypeField>
                                <ValidTypeField formNumber={formNumber} fieldNumber={0} changeForm={this.handleFormChange} nameField={"employeeTypeSpecName"} label={"Специальность работника:"} placeholder={"Введите специальность работника"} changeValidity={this.handleValidity} valid={this.state.employeeTypeSpecName.valid}></ValidTypeField>
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

Form13.propTypes = {
    formNumber : PropTypes.number.isRequired,
    formType : PropTypes.number.isRequired,
};

export default Form13;