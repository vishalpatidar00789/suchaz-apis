import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { CountrySuchaz } from './country-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<CountrySuchaz>;

@Injectable()
export class CountrySuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/countries';

    constructor(private http: HttpClient) { }

    create(country: CountrySuchaz): Observable<EntityResponseType> {
        const copy = this.convert(country);
        return this.http.post<CountrySuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(country: CountrySuchaz): Observable<EntityResponseType> {
        const copy = this.convert(country);
        return this.http.put<CountrySuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<CountrySuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<CountrySuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<CountrySuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CountrySuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: CountrySuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<CountrySuchaz[]>): HttpResponse<CountrySuchaz[]> {
        const jsonResponse: CountrySuchaz[] = res.body;
        const body: CountrySuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to CountrySuchaz.
     */
    private convertItemFromServer(country: CountrySuchaz): CountrySuchaz {
        const copy: CountrySuchaz = Object.assign({}, country);
        return copy;
    }

    /**
     * Convert a CountrySuchaz to a JSON which can be sent to the server.
     */
    private convert(country: CountrySuchaz): CountrySuchaz {
        const copy: CountrySuchaz = Object.assign({}, country);
        return copy;
    }
}
