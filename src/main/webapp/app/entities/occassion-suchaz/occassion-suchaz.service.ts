import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { OccassionSuchaz } from './occassion-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<OccassionSuchaz>;

@Injectable()
export class OccassionSuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/occassions';

    constructor(private http: HttpClient) { }

    create(occassion: OccassionSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(occassion);
        return this.http.post<OccassionSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(occassion: OccassionSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(occassion);
        return this.http.put<OccassionSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<OccassionSuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<OccassionSuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<OccassionSuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OccassionSuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: OccassionSuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<OccassionSuchaz[]>): HttpResponse<OccassionSuchaz[]> {
        const jsonResponse: OccassionSuchaz[] = res.body;
        const body: OccassionSuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to OccassionSuchaz.
     */
    private convertItemFromServer(occassion: OccassionSuchaz): OccassionSuchaz {
        const copy: OccassionSuchaz = Object.assign({}, occassion);
        return copy;
    }

    /**
     * Convert a OccassionSuchaz to a JSON which can be sent to the server.
     */
    private convert(occassion: OccassionSuchaz): OccassionSuchaz {
        const copy: OccassionSuchaz = Object.assign({}, occassion);
        return copy;
    }
}
