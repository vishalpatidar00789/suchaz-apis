import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { RelationshipImageSuchaz } from './relationship-image-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<RelationshipImageSuchaz>;

@Injectable()
export class RelationshipImageSuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/relationship-images';

    constructor(private http: HttpClient) { }

    create(relationshipImage: RelationshipImageSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(relationshipImage);
        return this.http.post<RelationshipImageSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(relationshipImage: RelationshipImageSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(relationshipImage);
        return this.http.put<RelationshipImageSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<RelationshipImageSuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<RelationshipImageSuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<RelationshipImageSuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<RelationshipImageSuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: RelationshipImageSuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<RelationshipImageSuchaz[]>): HttpResponse<RelationshipImageSuchaz[]> {
        const jsonResponse: RelationshipImageSuchaz[] = res.body;
        const body: RelationshipImageSuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to RelationshipImageSuchaz.
     */
    private convertItemFromServer(relationshipImage: RelationshipImageSuchaz): RelationshipImageSuchaz {
        const copy: RelationshipImageSuchaz = Object.assign({}, relationshipImage);
        return copy;
    }

    /**
     * Convert a RelationshipImageSuchaz to a JSON which can be sent to the server.
     */
    private convert(relationshipImage: RelationshipImageSuchaz): RelationshipImageSuchaz {
        const copy: RelationshipImageSuchaz = Object.assign({}, relationshipImage);
        return copy;
    }
}
