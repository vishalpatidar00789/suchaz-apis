import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { SuchAzUserSuchaz } from './such-az-user-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<SuchAzUserSuchaz>;

@Injectable()
export class SuchAzUserSuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/such-az-users';

    constructor(private http: HttpClient) { }

    create(suchAzUser: SuchAzUserSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(suchAzUser);
        return this.http.post<SuchAzUserSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(suchAzUser: SuchAzUserSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(suchAzUser);
        return this.http.put<SuchAzUserSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<SuchAzUserSuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<SuchAzUserSuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<SuchAzUserSuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<SuchAzUserSuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: SuchAzUserSuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<SuchAzUserSuchaz[]>): HttpResponse<SuchAzUserSuchaz[]> {
        const jsonResponse: SuchAzUserSuchaz[] = res.body;
        const body: SuchAzUserSuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to SuchAzUserSuchaz.
     */
    private convertItemFromServer(suchAzUser: SuchAzUserSuchaz): SuchAzUserSuchaz {
        const copy: SuchAzUserSuchaz = Object.assign({}, suchAzUser);
        return copy;
    }

    /**
     * Convert a SuchAzUserSuchaz to a JSON which can be sent to the server.
     */
    private convert(suchAzUser: SuchAzUserSuchaz): SuchAzUserSuchaz {
        const copy: SuchAzUserSuchaz = Object.assign({}, suchAzUser);
        return copy;
    }
}
