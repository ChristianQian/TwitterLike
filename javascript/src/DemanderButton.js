import React, { Component } from 'react';
import './form.css';
import axios from 'axios';

export default class DemanderButton extends Component{
    constructor(props){
        super(props);
        this.state={id:this.props.ui,u:this.props.u,k:this.props.ky}
    }

    send(){
        axios.get("http://localhost:8080/tme2/DemandeAmis?key="+this.state.k+"&id2="+this.state.id).then(response=>{
        if(response.data.new){
                this.props.e(this.state.id);
                this.props.a(this.state.u);
                this.props.m();
				console.log("demander");
        }else{
				console.log("non demander");
		}}).catch(response=>{alert(response)})
    }

    render(){
        return(<button className="friendR" onClick={()=>this.send()}> {this.props.un} {this.props.up} </button>)
    }
}