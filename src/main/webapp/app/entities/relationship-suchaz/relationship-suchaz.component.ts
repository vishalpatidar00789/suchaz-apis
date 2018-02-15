import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { RelationshipSuchaz } from './relationship-suchaz.model';
import { RelationshipSuchazService } from './relationship-suchaz.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-relationship-suchaz',
    templateUrl: './relationship-suchaz.component.html'
})
export class RelationshipSuchazComponent implements OnInit, OnDestroy {
relationships: RelationshipSuchaz[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private relationshipService: RelationshipSuchazService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.relationshipService.query().subscribe(
            (res: HttpResponse<RelationshipSuchaz[]>) => {
                this.relationships = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInRelationships();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: RelationshipSuchaz) {
        return item.id;
    }
    registerChangeInRelationships() {
        this.eventSubscriber = this.eventManager.subscribe('relationshipListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
