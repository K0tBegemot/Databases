import React from "react";
import PropTypes from "prop-types"
import TypeField from "./TypeField";
import SendButton from "./SendButton";
import AddButton from "./AddButton";
import DataFieldRange from "./DataFieldRange";
import DynamicTable from "./DynamicTable";

class Form4 extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            headerNames : ["Статус запроса", "Сообщение"],
            rowArray : [{operationStatus : "", message : ""}],

            headerNames2 : ["Имя цеха", "Имя участка цеха", "Идентификационный номер бригады", "Идентификационный номер работника", "Имя работника", "Фамилия работника"],
            rowArray2 : [{workshopName : "", workshopSectionName : "", brigadeId : "", workerId : "", firstName : "", secondName : ""}],

            typeCompanyFields : [{workshop : "Все"}]
        }
    }

    handleFormChange = (event, index) => {
        let data = [...this.state.typeCompanyFields];
        data[index][event.target.name] = event.target.value;
        this.setState({typeCompanyFields : data});
    }

    sendForm = (event) => {
        event.preventDefault();
        const data = this.state;
        const formType = this.props.formType;
        fetch(`http://localhost:8080/form/3`,
            {
                method : "POST",
                mode : "cors",
                cache : "default",
                headers : {
                    "Content-Type" : "application/json",
                },
                body : JSON.stringify({typeCompanyFields : this.state.typeCompanyFields})
            }
        ).then((response) => {return response.json()}).then((response) => {
            console.log(response);
            this.setState({rowArray : [response["status"]], rowArray2 : response["brigadeList"]});
        });
    }

    addNewField = (event) => {
        event.preventDefault();
        let data = [...this.state.typeCompanyFields, {workshop : "Все"}];
        this.setState({typeCompanyFields : data});
    }

    render() {
        const formNumber = this.props.formNumber;
        return (
            <>
                <form className={"row justify-content-center"}>
                    <div className={"row col-6 shadow-lg rounded-2"}>
                        <label htmlFor={`TypeCompanyField-${formNumber}`} className={"form-label"}>
                            <h4>Бригады из указанного цеха(ов):</h4>
                        </label>
                        <div id={`EmployeeWorkshopField-${formNumber}`}>
                            <div>
                                {this.state.typeCompanyFields.map((value, index) => {
                                    return(
                                        <>
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

Form4.propTypes = {
    formNumber : PropTypes.number.isRequired,
    formType : PropTypes.number.isRequired,
};

export default Form4;