import React from "react";
import PropTypes from "prop-types"
import TypeField from "./TypeField";
import SendButton from "./SendButton";
import AddButton from "./AddButton";
import DataFieldRange from "./DataFieldRange";
import DynamicTable from "./DynamicTable";

class Form1 extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            headerNames : ["Статус запроса", "Сообщение"],
            rowArray : [{operationStatus : "", message : ""}],

            headerNames2 : ["Имя цеха", "Имя участка цеха", "Тип продукта", "Модель продукта", "Количество едениц модели продукта"],
            rowArray2 : [{workshopName : "", workshopSectionName : "", productTypeName : "", productTypeModelName : "", productItemsNumber : ""}],

            typeCompanyFields : [{type : "Все", workshop : "Все"}],
            date : {isValid : false, startDate : (new Date().toISOString()), endDate : (new Date().toISOString())}
        }
    }

    handleFormChange = (event, index) => {
        let data = [...this.state.typeCompanyFields];
        data[index][event.target.name] = event.target.value;
        this.setState({typeCompanyFields : data});
    }

    handleDateChange = (date, name) => {
        let data = this.state.date;
        if(date != null)
        {
            data[name] = date.toISOString();
        }
        this.setState({date : data});
    }

    handleDateValidity = (event, index) => {
        let data = this.state.date;
        data["isValid"] = !(data["isValid"]);
        this.setState({date : data});
    }

    sendForm = (event) => {
        event.preventDefault();
        const formType = this.props.formType;
        fetch(`http://localhost:8080/form/0`,
            {
                method : "POST",
                mode : "cors",
                cache : "default",
                headers : {
                    "Content-Type" : "application/json",
                },
                body : JSON.stringify({typeCompanyFields : this.state.typeCompanyFields, date : this.state.date})
            }
        ).then((response) => {return response.json()}).then((response) => {
            console.log(response);
            this.setState({rowArray : [response["status"]], rowArray2 : response["productList"]});
        });
    }

    addNewField = (event) => {
        event.preventDefault();
        let data = [...this.state.typeCompanyFields, {type : "Все", workshop : "Все"}];
        this.setState({typeCompanyFields : data});
    }

    render() {
        const formNumber = this.props.formNumber;
        return (
            <>
                <form className={"row justify-content-center"}>
                    <div className={"row col-6 shadow-lg rounded-2"}>
                        <label htmlFor={`TypeCompanyField-${formNumber}`} className={"form-label"}>
                            <h4>Тип изделия и цех:</h4>
                        </label>
                        <div id={`TypeCompanyField-${formNumber}`}>
                            <div>
                                <DataFieldRange formNumber={formNumber} fieldNumber={0} changeForm={this.handleDateChange} changeValidity={this.handleDateValidity} isValid={this.state.date.isValid} nameField={"date"} label={"Начало и конец интервала времени:"}></DataFieldRange>
                                {this.state.typeCompanyFields.map((value, index) => {
                                    return(
                                        <>
                                            <TypeField formNumber={formNumber} fieldNumber={index} changeForm={this.handleFormChange} nameField={"type"} label={"Тип изделия:"} placeholder={"Все"}></TypeField>
                                            <TypeField formNumber={formNumber} fieldNumber={index} changeForm={this.handleFormChange} nameField={"workshop"} label={"Цех(а) предприятия:"} placeholder={"Все"}></TypeField>
                                        </>
                                    )
                                })}
                                <div className={"row"}>
                                    <div className={"col"}>
                                        <AddButton addData={this.addNewField}></AddButton>
                                    </div>
                                    <div className={"col"}>
                                        <SendButton sendForm={this.sendForm}></SendButton>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <DynamicTable headerNamesList={this.state.headerNames} BodyValues={this.state.rowArray}></DynamicTable>
                        <DynamicTable headerNamesList={this.state.headerNames2} BodyValues={this.state.rowArray2}></DynamicTable>
                    </div>
                </form>
            </>
        );
    }
}

Form1.propTypes = {
    formNumber : PropTypes.number.isRequired,
    formType : PropTypes.number.isRequired,
};

export default Form1;