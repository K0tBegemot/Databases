import React from "react";
import PropTypes from "prop-types";

class AddButton extends React.Component
{
    render() {
        return (
            <div className={""}>
                <button type={"button"} className={"btn btn-dark text-light"} onClick={this.props.addData}>Add Field</button>
            </div>
        );
    }
}

AddButton.propTypes = {
    addData : PropTypes.func.isRequired,
}

export default AddButton;