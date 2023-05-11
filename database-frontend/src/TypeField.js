import React from "react";
import PropTypes from "prop-types";

class TypeField extends React.Component{

    render() {
        const formNumber = this.props.formNumber;
        const fieldNumber = this.props.fieldNumber;
        const changeForm = this.props.changeForm;
        const fieldName = this.props.nameField;
        const label = this.props.label;
        const placeholder = this.props.placeholder;
        return (
            <div>
                <label htmlFor={`${fieldName}Field-${formNumber}-${fieldNumber}`} className={"form-label"}>
                    {label}
                </label>
                <input id={`${fieldName}Field-${formNumber}-${fieldNumber}`} type={"text"} className={"form-control"} name={fieldName} placeholder={placeholder} onChange={(event) => {changeForm(event, fieldNumber)}}/>
            </div>
        );
    }
}

TypeField.propTypes = {
    formNumber : PropTypes.number.isRequired,
    fieldNumber : PropTypes.number.isRequired,
    changeForm : PropTypes.func.isRequired,
    nameField : PropTypes.string.isRequired,
    label : PropTypes.string.isRequired,
    placeholder : PropTypes.string.isRequired,
}

export default TypeField;