import React, { Component } from 'react';
import './form.css';
import axios from 'axios';

export default class RemoveButton extends Component{

    send(){
        axios.get("http://localhost:8080/tme2/RemoveFriend?key="+this.props.kv+"&id2="+this.props.u.id).then(response=>{
        if(response.data.sup){
                this.props.ae(this.props.u.id,this.props.u);
                this.props.m();
				console.log("retire ");
        }else{
                console.log("non retire K "+this.props.kv);
        }
    }).catch(err=>{alert(err)})
    }

    render(){
        return(<button className="friendR" onClick={()=>this.send()}> {this.props.u.nom} {this.props.u.prenom} </button>)
    }
}