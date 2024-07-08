import React from 'react';
import './App.css';  
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import ListEmployeeComponent from './components/ListEmployeeComponent';
import AddEmployeeComponent from './components/AddEmployeeComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';

function App() {
  return (
    <div>
      <Router>
        <HeaderComponent />
        <div className='container'>
          <Routes>
            <Route exact path="/" Component={ListEmployeeComponent }></Route>
            <Route path="/employees" Component={ListEmployeeComponent }></Route>
            <Route path="/add-employee" Component={AddEmployeeComponent }></Route>
            <Route path="/edit-employee/:id" Component={AddEmployeeComponent}></Route>
          </Routes>
        </div>
        <FooterComponent />
      </Router>
    </div>
  );
}

export default App;
