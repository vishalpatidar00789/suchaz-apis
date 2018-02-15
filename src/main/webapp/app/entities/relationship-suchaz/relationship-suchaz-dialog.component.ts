import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { RelationshipSuchaz } from './relationship-suchaz.model';
import { RelationshipSuchazPopupService } from './relationship-suchaz-popup.service';
import { RelationshipSuchazService } from './relationship-suchaz.service';

@Component({
    selector: 'jhi-relationship-suchaz-dialog',
    templateUrl: './relationship-suchaz-dialog.component.html'
})
export class RelationshipSuchazDialogComponent implements OnInit {

    relationship: RelationshipSuchaz;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private relationshipService: RelationshipSuchazService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.relationship.id !== undefined) {
            this.subscribeToSaveResponse(
                this.relationshipService.update(this.relationship));
        } else {
            this.subscribeToSaveResponse(
                this.relationshipService.create(this.relationship));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<RelationshipSuchaz>>) {
        result.subscribe((res: HttpResponse<RelationshipSuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: RelationshipSuchaz) {
        this.eventManager.broadcast({ name: 'relationshipListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-relationship-suchaz-popup',
    template: ''
})
export class RelationshipSuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private relationshipPopupService: RelationshipSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.relationshipPopupService
                    .open(RelationshipSuchazDialogComponent as Component, params['id']);
            } else {
                this.relationshipPopupService
                    .open(RelationshipSuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
