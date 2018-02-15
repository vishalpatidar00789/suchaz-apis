/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { RelationshipImageSuchazDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/relationship-image-suchaz/relationship-image-suchaz-delete-dialog.component';
import { RelationshipImageSuchazService } from '../../../../../../main/webapp/app/entities/relationship-image-suchaz/relationship-image-suchaz.service';

describe('Component Tests', () => {

    describe('RelationshipImageSuchaz Management Delete Component', () => {
        let comp: RelationshipImageSuchazDeleteDialogComponent;
        let fixture: ComponentFixture<RelationshipImageSuchazDeleteDialogComponent>;
        let service: RelationshipImageSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [RelationshipImageSuchazDeleteDialogComponent],
                providers: [
                    RelationshipImageSuchazService
                ]
            })
            .overrideTemplate(RelationshipImageSuchazDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(RelationshipImageSuchazDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RelationshipImageSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
