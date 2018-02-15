import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { RelationshipSuchaz } from './relationship-suchaz.model';
import { RelationshipSuchazService } from './relationship-suchaz.service';

@Component({
    selector: 'jhi-relationship-suchaz-detail',
    templateUrl: './relationship-suchaz-detail.component.html'
})
export class RelationshipSuchazDetailComponent implements OnInit, OnDestroy {

    relationship: RelationshipSuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private relationshipService: RelationshipSuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInRelationships();
    }

    load(id) {
        this.relationshipService.find(id)
            .subscribe((relationshipResponse: HttpResponse<RelationshipSuchaz>) => {
                this.relationship = relationshipResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInRelationships() {
        this.eventSubscriber = this.eventManager.subscribe(
            'relationshipListModification',
            (response) => this.load(this.relationship.id)
        );
    }
}
