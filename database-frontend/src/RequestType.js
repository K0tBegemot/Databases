import React from "react";
import PropTypes from "prop-types";
import RequestTypePicker from "./RequestTypePicker";

class RequestType extends React.Component
{
    render() {
        const formNumber = this.props.formNumber;
        const fieldNumber = this.props.fieldNumber;
        const changeForm1 = this.props.changeForm;
        const fieldName = this.props.nameField;
        return (
            <div>
                <RequestTypePicker formNumber={formNumber} fieldNumber={fieldNumber} changeForm={changeForm1} nameField={fieldName} label={"Вставка"} valid={"insert"} currentValue={this.props.currentValue}></RequestTypePicker>
                <RequestTypePicker formNumber={formNumber} fieldNumber={fieldNumber} changeForm={changeForm1} nameField={fieldName} label={"Изменение"} valid={"update"} currentValue={this.props.currentValue}></RequestTypePicker>
                <RequestTypePicker formNumber={formNumber} fieldNumber={fieldNumber} changeForm={changeForm1} nameField={fieldName} label={"Удаление"} valid={"delete"} currentValue={this.props.currentValue}></RequestTypePicker>
            </div>
        );
    }
}

RequestType.propTypes = {
    formNumber : PropTypes.number.isRequired,
    fieldNumber : PropTypes.number.isRequired,
    nameField : PropTypes.string.isRequired,
    currentValue : PropTypes.string.isRequired,
}

export default RequestType;