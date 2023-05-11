import React from "react";
import PropTypes from "prop-types";
import DynamicHeaders from "./DynamicHeaders";
import DynamicBody from "./DynamicBody";

class DynamicTable extends  React.Component
{
    render() {
        return (
            <table className="table table-stripped-columns table-bordered">
                <DynamicHeaders headerNamesList={this.props.headerNamesList}></DynamicHeaders>
                <DynamicBody BodyValues={this.props.BodyValues} MaxColumnNumber={this.props.headerNamesList.length}></DynamicBody>
            </table>
        );
    }
}

DynamicTable.propTypes = {
    headerNamesList : PropTypes.array.isRequired,
    BodyValues : PropTypes.array.isRequired
}

export default DynamicTable;