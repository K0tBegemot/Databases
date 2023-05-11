import React from "react";
import InitialPage from "./InitialPage";
import RequestsPage from "./RequestsPage";
import NavigationBar from "./NavigationBar";
import {Row, Tab, TabContainer, Tabs} from "react-bootstrap";

class TabbedMain extends React.Component{
    constructor(props, context) {
        super(props, context);
        this.state = {
            currentPage : "#startPage"
        }
    }

    render() {
        return (
            <>
                <NavigationBar></NavigationBar>
            </>
        );
    }
}

export default TabbedMain