import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { SuchAzMenuSuchaz } from './such-az-menu-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<SuchAzMenuSuchaz>;

@Injectable()
export class SuchAzMenuSuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/such-az-menus';

    constructor(private http: HttpClient) { }

    create(suchAzMenu: SuchAzMenuSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(suchAzMenu);
        return this.http.post<SuchAzMenuSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(suchAzMenu: SuchAzMenuSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(suchAzMenu);
        return this.http.put<SuchAzMenuSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<SuchAzMenuSuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<SuchAzMenuSuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<SuchAzMenuSuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<SuchAzMenuSuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: SuchAzMenuSuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<SuchAzMenuSuchaz[]>): HttpResponse<SuchAzMenuSuchaz[]> {
        const jsonResponse: SuchAzMenuSuchaz[] = res.body;
        const body: SuchAzMenuSuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to SuchAzMenuSuchaz.
     */
    private convertItemFromServer(suchAzMenu: SuchAzMenuSuchaz): SuchAzMenuSuchaz {
        const copy: SuchAzMenuSuchaz = Object.assign({}, suchAzMenu);
        return copy;
    }

    /**
     * Convert a SuchAzMenuSuchaz to a JSON which can be sent to the server.
     */
    private convert(suchAzMenu: SuchAzMenuSuchaz): SuchAzMenuSuchaz {
        const copy: SuchAzMenuSuchaz = Object.assign({}, suchAzMenu);
        return copy;
    }
}
