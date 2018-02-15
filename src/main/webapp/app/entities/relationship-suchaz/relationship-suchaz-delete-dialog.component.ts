import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { RelationshipSuchaz } from './relationship-suchaz.model';
import { RelationshipSuchazPopupService } from './relationship-suchaz-popup.service';
import { RelationshipSuchazService } from './relationship-suchaz.service';

@Component({
    selector: 'jhi-relationship-suchaz-delete-dialog',
    templateUrl: './relationship-suchaz-delete-dialog.component.html'
})
export class RelationshipSuchazDeleteDialogComponent {

    relationship: RelationshipSuchaz;

    constructor(
        private relationshipService: RelationshipSuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.relationshipService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'relationshipListModification',
                content: 'Deleted an relationship'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-relationship-suchaz-delete-popup',
    template: ''
})
export class RelationshipSuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private relationshipPopupService: RelationshipSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.relationshipPopupService
                .open(RelationshipSuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
