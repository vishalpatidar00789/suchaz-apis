import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { RelationshipImageSuchaz } from './relationship-image-suchaz.model';
import { RelationshipImageSuchazPopupService } from './relationship-image-suchaz-popup.service';
import { RelationshipImageSuchazService } from './relationship-image-suchaz.service';

@Component({
    selector: 'jhi-relationship-image-suchaz-delete-dialog',
    templateUrl: './relationship-image-suchaz-delete-dialog.component.html'
})
export class RelationshipImageSuchazDeleteDialogComponent {

    relationshipImage: RelationshipImageSuchaz;

    constructor(
        private relationshipImageService: RelationshipImageSuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.relationshipImageService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'relationshipImageListModification',
                content: 'Deleted an relationshipImage'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-relationship-image-suchaz-delete-popup',
    template: ''
})
export class RelationshipImageSuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private relationshipImagePopupService: RelationshipImageSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.relationshipImagePopupService
                .open(RelationshipImageSuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
