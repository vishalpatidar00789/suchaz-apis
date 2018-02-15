import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { RelationshipSuchaz } from './relationship-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<RelationshipSuchaz>;

@Injectable()
export class RelationshipSuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/relationships';

    constructor(private http: HttpClient) { }

    create(relationship: RelationshipSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(relationship);
        return this.http.post<RelationshipSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(relationship: RelationshipSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(relationship);
        return this.http.put<RelationshipSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<RelationshipSuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<RelationshipSuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<RelationshipSuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<RelationshipSuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: RelationshipSuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<RelationshipSuchaz[]>): HttpResponse<RelationshipSuchaz[]> {
        const jsonResponse: RelationshipSuchaz[] = res.body;
        const body: RelationshipSuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to RelationshipSuchaz.
     */
    private convertItemFromServer(relationship: RelationshipSuchaz): RelationshipSuchaz {
        const copy: RelationshipSuchaz = Object.assign({}, relationship);
        return copy;
    }

    /**
     * Convert a RelationshipSuchaz to a JSON which can be sent to the server.
     */
    private convert(relationship: RelationshipSuchaz): RelationshipSuchaz {
        const copy: RelationshipSuchaz = Object.assign({}, relationship);
        return copy;
    }
}
