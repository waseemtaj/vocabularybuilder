import React, { Component } from "react";

import wordSearch from "./Search";
import SearchResults from "./SearchResults"

export default class SearchForm  extends Component {

    constructor(props) {
        super(props);
        this.state = {query: '', data: []};
        this.handleQueryChange = this.handleQueryChange.bind(this)
        this.doSearch = this.doSearch.bind(this)
        this.submitQuery = this.submitQuery.bind(this)
    }

    doSearch() {
        const that = this;
        wordSearch(this.state.query.trim(), function(data) {
            that.setState({data: data.results});
        });
    }

    handleQueryChange(e) {
        e.preventDefault();
        this.setState(
            {query: e.target.value}
            //() => { if (this.state.query) {this.doSearch();}}
        );
    }

    submitQuery(e) {
        e.preventDefault();
        if (this.state.query) {
            this.doSearch();
        }
    }

    render() {
        return (
            <div className="search-container">
                <label>Search for: </label>
                <input
                    type="text"
                    placeholder="type a word"
                    value={this.state.query}
                    onChange={this.handleQueryChange}
                    onBlur={this.submitQuery}
                />
                <SearchResults data={this.state.data} />
            </div>
        );
    }

}