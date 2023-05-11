import React from "react";
import {Tab, Tabs} from "react-bootstrap";
import InitialPage from "./InitialPage";
import Form1 from "./Form1";
import Form2 from "./Form2";
import Form3 from "./Form3";
import Form4 from "./Form4";
import Form5 from "./Form5";
import Form6 from "./Form6";
import Form7 from "./Form7";
import Form8 from "./Form8";
import Form9 from "./Form9";
import Form10 from "./Form10";

class RequestsPage extends React.Component{
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <>
                <nav>
                    <div className="nav nav-tabs bg-dark navbar-expand-lg bg-body-tertiary" id="nav-tab" role="tablist">
                        <button className="btn btn-dark text-light nav-item active" id="nav-form1-tab" data-bs-toggle="tab"
                                data-bs-target="#nav-form1" type="button" role="tab" aria-controls="nav-form1"
                                aria-selected="true">Изделия
                        </button>
                        <button className="btn btn-dark text-light nav-item" id="nav-form2-tab" data-bs-toggle="tab" data-bs-target="#nav-form2"
                                type="button" role="tab" aria-controls="nav-form2" aria-selected="false">Сотрудники
                        </button>
                        <button className="btn btn-dark text-light nav-item" id="nav-form3-tab" data-bs-toggle="tab" data-bs-target="#nav-form3"
                                type="button" role="tab" aria-controls="nav-form3" aria-selected="false">Участки
                        </button>
                        <button className="btn btn-dark text-light nav-item" id="nav-form4-tab" data-bs-toggle="tab" data-bs-target="#nav-form4"
                                type="button" role="tab" aria-controls="nav-form4" aria-selected="false">Бригады
                        </button>
                        <button className="btn btn-dark text-light nav-item" id="nav-form5-tab" data-bs-toggle="tab" data-bs-target="#nav-form5"
                                type="button" role="tab" aria-controls="nav-form5" aria-selected="false">Мастера
                        </button>
                        <button className="btn btn-dark text-light nav-item" id="nav-form6-tab" data-bs-toggle="tab" data-bs-target="#nav-form6"
                                type="button" role="tab" aria-controls="nav-form6" aria-selected="false">Изделие и данные
                        </button>
                        <button className="btn btn-dark text-light nav-item" id="nav-form7-tab" data-bs-toggle="tab" data-bs-target="#nav-form7"
                                type="button" role="tab" aria-controls="nav-form7" aria-selected="false">Лабораторные испытания изделия
                        </button>
                        <button className="btn btn-dark text-light nav-item" id="nav-form8-tab" data-bs-toggle="tab" data-bs-target="#nav-form8"
                                type="button" role="tab" aria-controls="nav-form8" aria-selected="false">Испытания в лаборатории
                        </button>
                        <button className="btn btn-dark text-light nav-item" id="nav-form9-tab" data-bs-toggle="tab" data-bs-target="#nav-form9"
                                type="button" role="tab" aria-controls="nav-form9" aria-selected="false">Испытатели изделия
                        </button>
                        <button className="btn btn-dark text-light nav-item" id="nav-form10-tab" data-bs-toggle="tab" data-bs-target="#nav-form10"
                                type="button" role="tab" aria-controls="nav-form10" aria-selected="false">Оборудование для испытаний изделия
                        </button>
                    </div>
                </nav>
                <div className="tab-content" id="nav-tabContent">
                    <div className="tab-pane fade show active" id="nav-form1" role="tabpanel" aria-labelledby="nav-form1-tab"
                         tabIndex="0">
                        <Form1 formNumber={0} formType={0}></Form1>
                    </div>
                    <div className="tab-pane fade" id="nav-form2" role="tabpanel" aria-labelledby="nav-form2-tab"
                         tabIndex="1">
                        <Form2 formNumber={1} formType={1}></Form2>
                    </div>
                    <div className="tab-pane fade" id="nav-form3" role="tabpanel" aria-labelledby="nav-form3-tab"
                         tabIndex="2">
                        <Form3 formNumber={2} formType={2}></Form3>
                    </div>
                    <div className="tab-pane fade" id="nav-form4" role="tabpanel" aria-labelledby="nav-form4-tab"
                         tabIndex="3">
                        <Form4 formNumber={3} formType={3}></Form4>
                    </div>
                    <div className="tab-pane fade" id="nav-form5" role="tabpanel" aria-labelledby="nav-form5-tab"
                         tabIndex="4">
                        <Form5 formNumber={4} formType={4}></Form5>
                    </div>
                    <div className="tab-pane fade" id="nav-form6" role="tabpanel" aria-labelledby="nav-form6-tab"
                         tabIndex="5">
                        <Form6 formNumber={5} formType={5}></Form6>
                    </div>
                    <div className="tab-pane fade" id="nav-form7" role="tabpanel" aria-labelledby="nav-form7-tab"
                         tabIndex="6">
                        <Form7 formNumber={6} formType={6}></Form7>
                    </div>
                    <div className="tab-pane fade" id="nav-form8" role="tabpanel" aria-labelledby="nav-form8-tab"
                         tabIndex="7">
                        <Form8 formNumber={7} formType={7}></Form8>
                    </div>
                    <div className="tab-pane fade" id="nav-form9" role="tabpanel" aria-labelledby="nav-form9-tab"
                         tabIndex="8">
                        <Form9 formNumber={8} formType={8}></Form9>
                    </div>
                    <div className="tab-pane fade" id="nav-form10" role="tabpanel" aria-labelledby="nav-form10-tab"
                         tabIndex="9">
                        <Form10 formNumber={9} formType={9}></Form10>
                    </div>
                </div>
            </>
        );
    }
}

export default RequestsPage;