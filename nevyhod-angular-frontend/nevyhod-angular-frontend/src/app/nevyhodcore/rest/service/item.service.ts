import { HttpClient, HttpHeaders } from "@angular/common/http";
import { ItemVO } from "../../model/item-model";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

interface IService {
    findAll(): Observable<any>;
    findById(id: number): Observable<any>
}

export class MediaType {
    static text: string = 'text/plain';
    static json: string = 'application/json';
}

export class HttpOptions {
    public headers: HttpHeaders;
    public resType: string;

    static make4Json(): HttpOptions {
        let opt = new HttpOptions();
        opt.headers = new HttpHeaders({ 'Content-Type': MediaType.json, 'Accept': MediaType.json, 'Authorization': `Basic ${btoa('luke:luke')}` });
        opt.resType = 'json';
        return opt;
    }
    static make4Text(): HttpOptions {
        let opt = new HttpOptions();
        opt.headers = new HttpHeaders({ 'Content-Type': MediaType.text, 'Accept': MediaType.text, 'Authorization': `Basic ${btoa('luke:luke')}` });
        opt.resType = 'text';
        return opt;
    }

    get(): any {
        return {
            headers: this.headers,
            responseType: this.resType,
            
        }
    }
}

@Injectable()
export class ItemService implements IService {
    headers: HttpHeaders;
    options: any;
    baseUrl: string = "http://localhost:8080/rest/api/";
    options4Json: HttpOptions = HttpOptions.make4Json();
    options4Text: HttpOptions = HttpOptions.make4Text();

    constructor(private http: HttpClient) {
    }

    findAll(): Observable<any> {
        let suffix = "items";
        return this.http
            .get(this.baseUrl + suffix, this.options4Json.get());
    }
    findAll4Firebase(): Observable<any> {
        let suffix = "firebase/items";
        return this.http
            .get(this.baseUrl + suffix, this.options4Json.get());
    }
    test(): Observable<any> {
        let suffix = "test/cors";
        return this.http
            .get(this.baseUrl + suffix, this.options4Text.get());
    }
    findById(id: number): Observable<any> {
        let suffix = "firebase/items/2";
        return this.http
            .get(this.baseUrl + suffix, this.options4Json.get());
    }

}