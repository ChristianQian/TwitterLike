import React, { Component } from 'react';
import './form.css';
import axios from 'axios';

export default class AddButton extends Component{

    send(){
        axios.get("http://localhost:8080/tme2/AddFriend?key="+this.props.kv+"&id2="+this.props.u.id).then(response=>{
        if(response.data.new){
                this.props.a(this.props.u.id,this.props.u);
                this.props.m();
				console.log("ajoute ");
        }else{
				console.log("non ajoute  K "+this.props.kv);
		}}).catch(response=>{alert(response)})
    }

    render(){
        return(<button className="etranR" onClick={()=>this.send()}> {this.props.u.nom} {this.props.u.prenom} </button>)
    }
}